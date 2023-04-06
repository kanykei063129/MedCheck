package medCheck.dao.impl;

import medCheck.dao.DepartmentServiceDao;
import medCheck.dao.DoctorServiceDao;
import medCheck.dao.HospitalServiceDao;
import medCheck.database.Database;
import medCheck.enums.Gender;
import medCheck.exception.MyException;
import medCheck.model.Department;
import medCheck.model.Doctor;
import medCheck.model.Hospital;
import medCheck.service.DepartmentService;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;


public class DoctorServiceImplDao implements DoctorServiceDao {

    Department department1 = new Department(1L, "rtgdfgdf", new ArrayList<>(Arrays.asList(new Doctor(1L, "Amir", "Mirlanov", Gender.FEMALE, 1))));
    List<Department> depart = new ArrayList<>(Arrays.asList(department1));
    Hospital hospital1 = new Hospital(2L, "Republican Hospital No. 2", "Bishkek, st. Kyiv, 110", depart, new ArrayList<>(), new ArrayList<>());
    List<Hospital> hospitals = new ArrayList<>(Arrays.asList(hospital1));
    Database database = new Database(hospitals);

    @Override
    public String addDoctorToHospital(Long id, Doctor doctor) {
        for (Hospital h : database.getHospitals()) {

            if (Objects.equals(h.getId(), id)) {
                h.getDoctors().add(doctor);
                return "Doctor added to hospital successfully."+doctor;
            }
        }
        return "Hospital not found!";
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital h : database.getHospitals()) {
            for (Doctor d : h.getDoctors()) {
                if (d.getId() == id) {
                    return d;
                }
            }
        }
        return null;
    }

    @Override
    public String updateDoctor(Long id, Doctor doctor) {
        for (Hospital h : database.getHospitals()) {
            for (Doctor d : h.getDoctors()) {
                if (h.getId() == id) {
                    h.getDoctors().remove(d);
                    h.getDoctors().add(doctor);
                    return "Doctor updated successfully." + doctor;
                }
            }
        }
        return "Doctor not found!";
    }

    @Override
    public void deleteDoctorById(Long id) {
        boolean isTrue = true;
        for (Hospital h : database.getHospitals()) {
            for (Doctor d : h.getDoctors()) {
                if (d.getId() == id) {
                    isTrue = true;
                    h.getDoctors().remove(d);
                    System.out.println("Doctor removed successfully");
                    break;
                } else {
                    isTrue = false;
                }
            }
        }
        try {
            if (!isTrue) {
                throw new MyException("Doctor not found!");

            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital h : database.getHospitals()) {
            for (Department d : h.getDepartments()) {
                for (Doctor o : h.getDoctors()) {
                    if (d.getId() == departmentId) {
                        doctorsId.add(o.getId());
                        return "Doctor id added successfully" + doctorsId;
                    }
                }
            }
        }
        return "Invalid partition ID";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        boolean isTrue = true;

        for (Hospital h : database.getHospitals()) {
            if (Objects.equals(h.getId(), id)) {
                isTrue = true;
                return h.getDoctors();
            } else {
                isTrue = false;
            }
        }
        try {
            if (!isTrue) {
                throw new MyException("Hospital with id " + id + " does not exist.");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital h : database.getHospitals()) {
            for (Department d : h.getDepartments()) {
                if (Objects.equals(d.getId(), id)) {
                    return d.getDoctors();
                }
            }
        }

        return null;
    }
}
