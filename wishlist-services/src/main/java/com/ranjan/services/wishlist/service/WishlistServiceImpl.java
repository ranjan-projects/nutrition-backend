package com.ranjan.services.wishlist.service;

import com.ranjan.services.wishlist.entity.WishlistEntity;
import com.ranjan.services.wishlist.entity.WishlistId;
import com.ranjan.services.wishlist.exception.CodedException;
import com.ranjan.services.wishlist.model.Wishlist;
import com.ranjan.services.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ranjan.services.wishlist.exception.ExceptionEnum.WISHLIST_ALREADY_EXIST;
import static com.ranjan.services.wishlist.exception.ExceptionEnum.WISHLIST_NOT_FOUND;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<WishlistEntity> getAllWishlist(String userName) {
        return wishlistRepository.findByUsername(userName);
    }

//    @Override
//    public WishlistEntity getWishlistById(long id) {
//        Optional<WishlistEntity> wishlist = wishlistRepository.findById(id);
//        if (wishlist.isEmpty()) {
//            throw new CodedException(WISHLIST_NOT_FOUND.getResponseCode(), WISHLIST_NOT_FOUND.getReasonCode(), WISHLIST_NOT_FOUND.getMessage());
//        }
//
//        return wishlist.get();
//    }

    @Override
    public WishlistEntity addToWishlist(Wishlist wishlist) {

        WishlistId wishlistId = buildWishlistId(wishlist);
        Optional<WishlistEntity> existingWishlist = wishlistRepository.findById(wishlistId);
        if (existingWishlist.isPresent()) {
            throw new CodedException(WISHLIST_ALREADY_EXIST.getResponseCode(), WISHLIST_ALREADY_EXIST.getReasonCode(), WISHLIST_ALREADY_EXIST.getMessage());
        }

        return wishlistRepository.save(buildWishlistEntity(wishlist, wishlistId));
    }

//    @Override
//    public WishlistEntity updateWishlist(WishlistEntity wishlist) {
//        Optional<WishlistEntity> existingWishlist = wishlistRepository.findById(wishlist.getWishlistId());
//        if (existingWishlist.isEmpty()) {
//            throw new CodedException(WISHLIST_NOT_FOUND.getResponseCode(), WISHLIST_NOT_FOUND.getReasonCode(), WISHLIST_NOT_FOUND.getMessage());
//        }
//        return wishlistRepository.save(wishlist);
//    }

    @Override
    public WishlistEntity deleteWishlist(String foodName, String username) {
        WishlistId wishlistId = WishlistId.builder()
                .username(username)
                .foodName(foodName)
                .build();
        Optional<WishlistEntity> existingWishlist = wishlistRepository.findById(wishlistId);
        if (existingWishlist.isEmpty()) {
            throw new CodedException(WISHLIST_NOT_FOUND.getResponseCode(), WISHLIST_NOT_FOUND.getReasonCode(), WISHLIST_NOT_FOUND.getMessage());
        }
        wishlistRepository.deleteById(wishlistId);

        return existingWishlist.get();
    }

    private WishlistEntity buildWishlistEntity(Wishlist wishlist, WishlistId id) {
        return WishlistEntity.builder()
                .wishlistId(id)
                .foodName(wishlist.getFoodName())
                .username(wishlist.getUsername())
                .servingUnit(wishlist.getServingUnit())
                .servingQty(wishlist.getServingQty())
                .tagId(wishlist.getTagId())
                .tagName(wishlist.getTagName())
                .commonType(wishlist.getCommonType())
                .photoUrl(wishlist.getPhotoUrl())
                .locale(wishlist.getLocale())
                .build();
    }

    private WishlistId buildWishlistId(Wishlist wishlist) {
        return WishlistId.builder()
                .username(wishlist.getUsername())
                .foodName(wishlist.getFoodName())
                .build();
    }
}
