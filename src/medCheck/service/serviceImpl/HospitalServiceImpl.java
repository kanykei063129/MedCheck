package medCheck.service.serviceImpl;

import medCheck.dao.impl.HospitalServiceImplDao;
import medCheck.database.Database;
import medCheck.model.Hospital;
import medCheck.model.Patient;
import medCheck.service.HospitalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private Database database = new Database();

    public HospitalServiceImpl(Database database) {
        this.database = database;
    }

    HospitalServiceImplDao hospitalServiceImplDao = new HospitalServiceImplDao();

    @Override
    public String addHospital(Hospital hospital) {
       return hospitalServiceImplDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
       return hospitalServiceImplDao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
       return hospitalServiceImplDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
       return hospitalServiceImplDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalServiceImplDao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
       return hospitalServiceImplDao.getAllHospitalByAddress(address);
    }
}
