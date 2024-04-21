package packages.project.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("customer", customer);
            return "customer_dashboard.html"; // View name without file extension
        } else {
            return "customer_not_found"; // Render error page if customer is not found
        }
    }




    @GetMapping("/api/customers/{loginId}")
    public String redirectToCustomerDashboard(@PathVariable Long loginId) {
        // Construct the redirect URL
        String redirectUrl = "/customers/" + loginId;

        // Redirect to the specified URL
        return "redirect:" + redirectUrl;
    }

}
