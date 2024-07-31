package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Cart;
import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.repositories.CartRepository;
import com.example.Java6_ASM.services.CartService;
import com.example.Java6_ASM.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Cart addItemToCart(Cart item) {
        Optional<Cart> existingItemOpt = cartRepository.findByAccountAndName(item.getAccount(), item.getName());

        if (existingItemOpt.isPresent()) {
            Product product = productService.findById(item.getProductId()).get();
            int totalQuantityProduct = product.getQuantity();
            Cart existingItem = existingItemOpt.get();;
            if (totalQuantityProduct > existingItem.getQuantity()) {
//                System.out.println(totalQuantityProduct);
//                System.out.println(existingItem.getQuantity());
                existingItem.setQuantity(existingItem.getQuantity() + 1);
            }else{
                existingItem.setQuantity(existingItem.getQuantity());
            }
            return cartRepository.save(existingItem);
        } else {
            item.setQuantity(1);
            return cartRepository.save(item);
        }
    }

    @Override
    public List<Cart> getAllItemInCart(UUID id) {
        return cartRepository.findAllByAccountId(id);
    }

    @Override
    public void deleteItemInCart(UUID itemID) {
        cartRepository.delete(findItemById(itemID));
    }

    @Override
    public Cart findItemById(UUID id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart reduceQuantityItemInCart(UUID itemID) {
        Cart item = findItemById(itemID);
        Optional<Cart> existingItemOpt = cartRepository.findByAccountAndName(item.getAccount(), item.getName());

        if (existingItemOpt.isPresent()) {
            Cart existingItem = existingItemOpt.get();
            if(existingItem.getQuantity() == 1) {
                deleteItemInCart(itemID);
                return null;
            }
            existingItem.setQuantity(existingItem.getQuantity() - 1);
            return cartRepository.save(existingItem);
        } else {
            item.setQuantity(1);
            return cartRepository.save(item);
        }
    }

    @Override
    public void deleteAllItemInCart() {
        cartRepository.deleteAll();
    }
}
