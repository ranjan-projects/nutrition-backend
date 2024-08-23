package com.ranjan.services.wishlist.service;

import com.ranjan.services.wishlist.entity.WishlistEntity;
import com.ranjan.services.wishlist.model.Wishlist;

import java.util.List;

public interface WishlistService {

    List<WishlistEntity> getAllWishlist(String userName);

//    WishlistEntity getWishlistById(long id);

    WishlistEntity addToWishlist(Wishlist wishlist);

    // WishlistEntity updateWishlist(WishlistEntity wishlist);

    WishlistEntity deleteWishlist(String foodName, String username);

}
