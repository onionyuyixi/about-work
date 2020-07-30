//package com.example.aboutwork.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//
//@Data
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "t_pd_bundle_item_sku")
//public class PdBundleItemSku implements Serializable {
//    @Id
//    @SequenceGenerator(name = "t_pd_bundle_manager_id_seq", sequenceName = "t_pd_bundle_manager_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_pd_bundle_manager_id_seq")
//    private Long id;
//    @Column(name = "item_id")
//    private Long itemId = 4l;
//    @Column(name = "sku_id")
//    private Long skuId = 2l;
//    @Column(name = "bundle_sale_price")
//    private BigDecimal bundleSalePrice = new BigDecimal(1343.00);
//    @Column(name = "parent_id")
//    private Long parentId;
//    @Column(name = "create_time")
//    private Date createTime = new Date();
//    @Column(name = "creator")
//    private Long creator = 1l;
//    @Column(name = "updater")
//    private Long updater = 1l;
//    @Column(name = "update_time")
//    private Date updateTime = new Date();
//
//}
