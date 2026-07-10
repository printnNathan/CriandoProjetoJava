package com.enventostec.api.Domain.event;

import java.util.Date;
import java.util.List;
import java.util.UUID;


    public record EventDetailsDTO(

            UUID id,
            String title,
            String description,
            Date date,
            String city,
            String uf,
            String imgUrl,
            String eventUrl,
            List<CouponDTO> coupon) {


        public record CouponDTO(
                String code,
                Integer discout,
                Date validUnti) {

        }
    }

