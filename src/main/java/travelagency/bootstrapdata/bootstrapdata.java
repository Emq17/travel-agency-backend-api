package travelagency.bootstrapdata;

import travelagency.dao.CustomerRepository;
import travelagency.dao.DivisionRepository;
import travelagency.entities.Customer;
import travelagency.entities.Division;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class bootstrapdata {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @PostConstruct
    public void createSampleCustomers() {
        List<Division> divisions = divisionRepository.findAll();
        if (divisions.size() < 7) {
            System.out.println("Insufficient number of divisions to assign to customers");
            return;
        }

        addCustomerIfNotExists("Justin", "James", "20381", "591 Street", "120-391-0024", divisions.get(4));
        addCustomerIfNotExists("Michael", "Scott", "89032", "455 Street", "599-923-8817", divisions.get(3));
        addCustomerIfNotExists("Robert", "Garfield", "90321", "289 Street", "592-211-5990", divisions.get(6));
        addCustomerIfNotExists("Andrew", "Collins", "81073", "328 Street", "494-132-0913", divisions.get(5));
        addCustomerIfNotExists("Kyle", "Thomas", "43132", "102 Street", "652-212-5212", divisions.get(2));
    }

    private void addCustomerIfNotExists(String firstName, String lastName, String postalCode, String address, String phone, Division division) {
        List<Customer> existingCustomers = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (existingCustomers.isEmpty()) {
            createCustomer(firstName, lastName, postalCode, address, phone, division);
            System.out.println("Added new customer: " + firstName + " " + lastName);
        } else {
            System.out.println("Customer already exists: " + firstName + " " + lastName);
        }
    }

    private void createCustomer(String firstName, String lastName, String postalCode, String address, String phone, Division division) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPostal_code(postalCode);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setDivision(division);
        customer.setCreate_date(new Date());
        customer.setLast_update(new Date());
        customerRepository.save(customer);
    }
}
