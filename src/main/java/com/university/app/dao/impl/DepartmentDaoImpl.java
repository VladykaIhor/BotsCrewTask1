package com.university.app.dao.impl;

import com.university.app.utils.HibernateUtil;
import com.university.app.dao.DepartmentDAO;
import com.university.app.entity.Department;
import com.university.app.entity.Lector;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDAO {

    private static final Logger logger = Logger.getLogger(DepartmentDaoImpl.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void addDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(department);
            session.close();
            logger.info("Department " + department.getName() + " was added to DB");
        } catch (Exception e) {
            logger.error("Failed to create department ", e);
        }
    }

    @Override
    public Optional<Department> getDepartmentByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Department where name =:name");
            query.setParameter("name", name);
            transaction.commit();
            Department department = (Department) query.getSingleResult();
            return Optional.ofNullable(department);
        } catch (Exception e) {
            logger.error("There is no department with this name", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Lector> getHeadOfDepartment(Department department) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Lector where department.headOfDepartment =:depHead");
            query.setParameter("depHead", department.getHeadOfDepartment());
            Lector lector = (Lector) query.getSingleResult();
            transaction.commit();
            return Optional.ofNullable(lector);
        } catch (Exception e) {
            logger.error("Failed to get head of a department");
        }
        return Optional.empty();
    }

    @Override
    public List<Department> getAllDepartments() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Department");
            List<Department> departmentList = query.getResultList();
            logger.info("List of all departments is shown");
            return departmentList;
        } catch (Exception e) {
            logger.error("Failed to get all departments");
        }
        return Collections.emptyList();
    }


    @Override
    public Optional<Double> showAverageSalaryForDepartment(Department department) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select l from Department dep join dep.lectors l" +
                    " where dep.departmentId =:id");
            query.setParameter("id", department.getDepartmentId());
            List<Lector> resultList = query.getResultList();
            int count = 0;
            Double sum = 0.0;
            for (Lector lector : resultList) {
                sum = lector.getSalary();
                count++;
            }
            transaction.commit();
            return Optional.of(sum / count);
        } catch (Exception e) {
            logger.error("Failed to get an average salary for " + department.getName() + "/n", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> showEmployeeCountForDepartment(Department department) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select l from Department dep join dep.lectors l" +
                    " where dep.departmentId =:id");
            query.setParameter("id", department.getDepartmentId());
            List<Lector> resultList = query.getResultList();
            transaction.commit();
            return Optional.of(resultList.size());
        } catch (Exception e) {
            logger.error("Failed to get department to get a list of employees", e);
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Integer> showDepartmentStatistics(Department department) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select l from Department dep join dep.lectors l" +
                    " where dep.departmentId =:id");
            query.setParameter("id", department.getDepartmentId());
            List<Lector> resultList = query.getResultList();
            Map<String, Integer> result = new HashMap<>();
            Query query2 = session.createQuery("select d.name from Degree d");
            List<String> degries = query2.getResultList();
            degries.forEach(x -> result.put(x, 0));
            for (Lector lector : resultList) {
                String degName = lector.getDegree().getName();
                result.put(degName, result.getOrDefault(degName, 0) + 1);
            }
            transaction.commit();
            return result;
        } catch (Exception e) {
            logger.error("There was an error while getting department statistics", e);
        }
        return Collections.EMPTY_MAP;
    }

    @Override
    public List<Lector> searchBy(String searchCriteria) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Lector as l where name like:searchCriteria or lastName like:searchCriteria");
            query.setParameter("searchCriteria", "%" + searchCriteria + "%");
            List<Lector> resultLectors = query.getResultList();
            Query query1 = session.createQuery("from Department as d where name like:searchCriteria");
            transaction.commit();
            return resultLectors;
        } catch (Exception e) {
            logger.error("Error while searching", e);
        }
        return Collections.EMPTY_LIST;
    }
}
