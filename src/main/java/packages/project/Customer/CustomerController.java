package packages.project.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("/customers/{loginId}")
    public String showCustomerDashboard(@PathVariable Integer loginId, Model model) {
        Customer customer = customerRepository.getCustomerByCustomerId(customerRepository.getCustomerIdByloginId(loginId));

        if (customer != null) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            model.addAttribute("customer", customer);

            return "customer_dashboard"; // View name without file extension
        } else {
            return "customer_not_found"; // Render error page if customer is not found
        }
    }

    @GetMapping("/customers/{loginId}/edit")
    public String showEditForm(@PathVariable Integer loginId , Model model) {
        // Add logic to retrieve customer information and pass it to the template
        Customer customer = customerRepository.getCustomerByCustomerId(customerRepository.getCustomerIdByloginId(loginId));
        model.addAttribute("customer", customer);
        return "customer_edit"; // Name of the edit form template
    }

    @PostMapping("/customers/{loginId}/edit")
    public String editCustomer(@PathVariable int loginId, @ModelAttribute Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.getCustomerByCustomerId(customerRepository.getCustomerIdByloginId(loginId));
        if (existingCustomer != null) {
            // Update the existing customer entity with the new information
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            // Similarly, update other fields as needed

            // Save the updated customer entity in the database
            customerRepository.save(existingCustomer);
        }
        // Redirect to the customer dashboard page after editing
        return "redirect:/customers/" + loginId;
    }
    @GetMapping("/api/customers/{loginId}")
    public String redirectToCustomerDashboard(@PathVariable Long loginId) {
        // Construct the redirect URL
        String redirectUrl = "/customers/" + loginId;

        // Redirect to the specified URL
        return "redirect:" + redirectUrl;
    }

}
