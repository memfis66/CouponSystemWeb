import { Component, OnInit } from '@angular/core';
import { Coupon } from '../../common/coupon';
import { CustomerService } from '../../customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  coupon: Coupon = new Coupon(null, "", null, null, null, "", "", null, "");
  coupons: Coupon[] = [];
  newCoupon: Coupon = new Coupon(null, "", null, null, null, "", "", null, "");
  newCoupons: Coupon[] = [];
  couponsList: Coupon[] = [];

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    // services initializaton for working without calling them
    this.getAllCouponsList();
    this.getAllMyCoupons();
  }

  getAllMyCoupons(): void {
    this.customerService.getAllMyCoupons()
    .subscribe(coupons => this.coupons = coupons);
  }

  getAllCouponsList(): void {
    this.customerService.getAllCouponsList()
    .subscribe(couponsList => this.couponsList = couponsList);
  }

 // calling getAllMyCoupons service in body for updates table with my coupons
  purchaseCoupon(coupon: Coupon): void {
    this.customerService.purchaseCoupon(coupon)
    .subscribe((coupon) => {
      console.log(coupon)
      this.customerService.getAllMyCoupons()
    .subscribe(coupons => this.coupons = coupons)
     } ); 
}

getAllMyCouponsByType(type: String) {
  this.customerService.getAllMyCouponsByType(this.newCoupon.type)
  .subscribe(newCoupons => this.newCoupons = newCoupons);
}

getAllMyCouponsByPrice(topPrice: number) {
  this.customerService.getAllMyCouponsByPrice(this.newCoupon.price)
  .subscribe(newCoupons => this.newCoupons = newCoupons);
}

getAllMyCouponsByDate(endDate: Date) {
  this.customerService.getAllMyCouponsByDate(this.newCoupon.endDate)
  .subscribe(newCoupons => this.newCoupons = newCoupons);
}

Logout(): void {
  this.customerService.Logout()
  .subscribe((resp)=>{
    console.log(resp)
    window.location.href='http://localhost:8080';
  })
}
}
