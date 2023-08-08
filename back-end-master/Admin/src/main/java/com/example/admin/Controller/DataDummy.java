package com.example.admin.Controller;


import com.example.library.DTO.MarketDTO;
import com.example.library.DTO.RequestOrder;
import com.example.library.DTO.UserDTO;
import com.example.library.Model.*;
import com.example.library.Repository.*;
import com.example.library.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataDummy implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MarketService marketService;


    @Override
    public void run(String... args) throws Exception {
        addUser();
        addRole();
        addCategory();
        addProduct();
        addOrder();
        addmarket();
    }

    public void addUser() {
        if (!userRepository.existsByUsername("khacthien")) {
            Users user1 = new Users();
            user1.setFullName("Tran Khac Thien");

            Calendar calendar = Calendar.getInstance();
            calendar.set(2001, Calendar.OCTOBER, 28);
            Date birthDate1 = calendar.getTime();
            user1.setBirthdate(birthDate1);

            user1.setPhoneNumber("1234567890");
            user1.setEmail("khacthien@gmail.com");
            user1.setUsername("khacthien");
            user1.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(user1);
        }

        if (!userRepository.existsByUsername("ducnguyen")) {
            Users user2 = new Users();
            user2.setFullName("Nguyễn Hữu Đức");

            Calendar calendar = Calendar.getInstance();
            calendar.set(2002, Calendar.DECEMBER, 3);
            Date birthDate1 = calendar.getTime();
            user2.setBirthdate(birthDate1);

            user2.setPhoneNumber("0378373822");
            user2.setEmail("ducnguyen@gmail.com");
            user2.setUsername("ducnguyen");
            user2.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(user2);
        }
    }

    public void addRole() {
        if (!roleRepository.existsByName("ADMIN")) {
            Role role_admin = new Role();
            role_admin.setName("ADMIN");
            roleRepository.save(role_admin);
        }
        if (!roleRepository.existsByName("USER")) {
            Role role_user = new Role();
            role_user.setName("USER");
            roleRepository.save(role_user);
        }
    }

    public void addCategory() {
        if (!categoryRepository.existsByName("LAPTOP")) {
            Category category = new Category();
            category.setName("LAPTOP");
            categoryRepository.save(category);
        }
        if (!categoryRepository.existsByName("MOBILE")) {
            Category category = new Category();
            category.setName("MOBILE");
            categoryRepository.save(category);
        }

    }

    public void addProduct() {

        if(!productRepository.existsByName("asus")){
            Product product = new Product();
            product.setName("asus");
            product.setPrice(10000000.00);
            product.setQuantity("10");
            product.setNote("");
            product.setCreate_time(convertToDate("26/05/2023"));
            product.setUpdate_time(convertToDate("22/12/2023"));
            productRepository.save(product);
        }

        if(!productRepository.existsByName("msi")){
            Product product = new Product();
            product.setName("msi");
            product.setPrice(15000000.00);
            product.setQuantity("10");
            product.setNote("");
            product.setCreate_time(convertToDate("26/05/2023"));
            product.setUpdate_time(convertToDate("07/08/2023"));
            productRepository.save(product);
        }

    }

    public void addOrder() {
        List<Order> orders = orderRepository.findByNameContaining("Tran Khac Thien");
        if(orders.size() == 0){
            RequestOrder requestOrder = new RequestOrder();

            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.THURSDAY, 3);
            Date ngaydat = calendar.getTime();

            requestOrder.setName("Tran Khac Thien");
            requestOrder.setNgaydat(ngaydat);
            requestOrder.setAddress("HCM");
            requestOrder.setPhonenumber("1234567890");
            requestOrder.setNote("");
            requestOrder.setTotalproducts("asus");
            requestOrder.setQuantity(3);
            requestOrder.setPrice(25000);
            requestOrder.setTotalprice(75000);
            orderService.createOrder(requestOrder);
        }

    }

    public void addmarket(){
        if (!marketRepository.existsByName("ABC")){
            MarketDTO marketDTO = new MarketDTO();
            marketDTO.setName("ABC");
            marketDTO.setPrice_increase(15000.00);
            marketService.createMarket(marketDTO);
        }
        if (!marketRepository.existsByName("XYZ")){
            MarketDTO marketDTO = new MarketDTO();
            marketDTO.setName("XYZ");
            marketDTO.setPrice_increase(15000.00);
            marketService.createMarket(marketDTO);
        }
    }

    public Timestamp convertToDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateObject = dateFormat.parse(date);
            Timestamp timestamp = new Timestamp(dateObject.getTime());
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}