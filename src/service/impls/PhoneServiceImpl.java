package service.impls;

import dao.impls.PhoneDaoImpl;
import models.Phone;
import service.PhoneService;

import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    private final PhoneDaoImpl phoneDao;

    public PhoneServiceImpl(PhoneDaoImpl phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public String addPhone(Phone phone) {
        return phoneDao.addPhone(phone);
    }

    @Override
    public Phone getPhoneById(Long phoneId) {
        return phoneDao.getPhoneById(phoneId);
    }

    @Override
    public Phone updatePhoneNameById(Long phoneId, String newName) {
        return phoneDao.updatePhoneNameById(phoneId, newName);
    }

    @Override
    public List<Phone> getAllPhones() {
        return phoneDao.getAllPhones();
    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        return phoneDao.getAllPhonesByBrand(brand);
    }

    @Override
    public void deletePhoneById(Long phoneId) {
       phoneDao.deletePhoneById(phoneId);
    }
}
