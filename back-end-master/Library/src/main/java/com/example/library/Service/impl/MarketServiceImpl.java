package com.example.library.Service.impl;

import com.example.library.DTO.MarketDTO;
import com.example.library.DTO.Product_MarketDTO;
import com.example.library.Model.Market;
import com.example.library.Model.Product;
import com.example.library.Model.ProductMarketId;
import com.example.library.Model.Product_Maket;
import com.example.library.Repository.MarketRepository;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.Product_MarketRepository;
import com.example.library.Service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private Product_MarketRepository productMarketRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Market> getAllMarket() {
        return marketRepository.findAll();
    }

    @Override
    public Market getMarket(Long id) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        if (optionalMarket.isPresent()){
            Market market = optionalMarket.get();
            return market;
        }
        return null;
    }

    @Override
    public String createMarket(MarketDTO marketDTO) {
        Market market = new Market();
        market.setName(marketDTO.getName());
        market.setPrice_increase(marketDTO.getPrice_increase());
        marketRepository.save(market);
        return "Create Market Success";
    }

    @Override
    public String updateMarket(Long id, MarketDTO marketDTO) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        if (optionalMarket.isPresent()){
            Market market = optionalMarket.get();
            market.setName(marketDTO.getName());
            market.setPrice_increase(marketDTO.getPrice_increase());
            marketRepository.save(market);
            return "Update Market Success";
        }
        return "Update Market Fail";
    }

    @Override
    public String deleteMarket(Long id) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        if (optionalMarket.isPresent()){
            Market market = optionalMarket.get();
            marketRepository.delete(market);
            return "Delete Market Success";
        }
        return "Delete Market Fail";
    }

    @Override
    public String updateProductMarket(Long idMarket, Long idProduct , Product_MarketDTO productMarketDTO) {
        Optional<Market> optionalMarket = marketRepository.findById(idMarket);
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if(optionalMarket.isPresent() && optionalProduct.isPresent()){
            Market market = optionalMarket.get();
            Product product = optionalProduct.get();

            ProductMarketId productMarketId = new ProductMarketId();
            productMarketId.setMarketId(market.getId());
            productMarketId.setProductId(product.getId());

            Product_Maket productMaket = new Product_Maket();
            productMaket.setProductMarketId(productMarketId);
            productMaket.setMarket(market);
            productMaket.setProduct(product);
            productMaket.setPriceOld(productMarketDTO.getPriceOld());
            productMaket.setPriceIncrease(productMarketDTO.getPriceIncrease());
            productMarketRepository.save(productMaket);
            return "Product market information updated successfully.";
        }
        return "Not found Market or Product";
    }

    @Override
    public String deleteProductMarket(Long idMarket, Long idProduct) {
        Optional<Market> optionalMarket = marketRepository.findById(idMarket);
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if(optionalMarket.isPresent() && optionalProduct.isPresent()){
            Market market = optionalMarket.get();
            Product product = optionalProduct.get();

            ProductMarketId productMarketId = new ProductMarketId();
            productMarketId.setMarketId(market.getId());
            productMarketId.setProductId(product.getId());

            Optional<Product_Maket> optionalProductMaket = productMarketRepository.findByProductMarketId(productMarketId);
            if(optionalMarket.isPresent()){
                Product_Maket productMaket = optionalProductMaket.get();
                productMarketRepository.delete(productMaket);
                return "Delete product from market successfully";
            }
            else
                return "Product market not found";
        }
        return "Product market not found";
    }

    @Override
    public List<Product> getListProduct_Market(Long id) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        if(optionalMarket.isPresent()){
            Market market = optionalMarket.get();

            List<Product_Maket> productMaket =  productMarketRepository.findAllByMarket(market);
            List<Product> List_products = new ArrayList<>();

            for (Product_Maket product_maket : productMaket){
                Product product = product_maket.getProduct();
                List_products.add(product);
            }
            return List_products;
        }
        return null;
    }
}
