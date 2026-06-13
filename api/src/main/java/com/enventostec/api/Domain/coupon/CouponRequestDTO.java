package com.enventostec.api.Domain.coupon;

public record CouponRequestDTO(String code, Integer discount, Long valid) {
}
