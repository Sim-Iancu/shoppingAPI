package shopifyapi.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopifyapi.model.Customer;
import shopifyapi.model.dao.CustomerRepository;

@Service
public class CustomerManager implements Manager<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer get(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
