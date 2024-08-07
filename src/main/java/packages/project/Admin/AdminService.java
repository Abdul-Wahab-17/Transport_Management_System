package packages.project.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import packages.project.Customer.Customer;
import packages.project.Customer.CustomerRepository;
import packages.project.Driver.Driver;
import packages.project.Driver.DriverRepository;
import packages.project.Vehicle.Vehicle;
import packages.project.Vehicle.VehicleRepository;

import java.util.List;

@Service
public class AdminService {
    AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    public Admin getAdmin( Integer loginId){
        Admin admin = adminRepository.getAdminByAdminId(adminRepository.getAdminIdByLoginId(loginId));
        return admin;
    }

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Autowired
    DriverRepository driverRepository;

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }



}
