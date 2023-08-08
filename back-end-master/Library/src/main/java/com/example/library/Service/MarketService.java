package com.example.library.Service;

import com.example.library.DTO.MarketDTO;
import com.example.library.DTO.Product_MarketDTO;
import com.example.library.Model.Market;
import com.example.library.Model.Product;
import com.example.library.Model.Product_Maket;

import java.util.List;

public interface MarketService {
    List<Market> getAllMarket();
    Market getMarket(Long id);
    String createMarket(MarketDTO marketDTO);
    String updateMarket(Long id, MarketDTO marketDTO);
    String deleteMarket(Long id);
    String updateProductMarket(Long idMarket , Long idProduct , Product_MarketDTO productMarketDTO);
    String deleteProductMarket(Long idMarket , Long idProduct);

    List<Product> getListProduct_Market(Long id);

}
