import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../company.service';
import { Coupon } from '../common/coupon';

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrls: ['./coupon.component.css']
})
export class CouponComponent implements OnInit {

  coupon: Coupon = new Coupon(null, "", null, null, null, "", "", null, "");
  coupons: Coupon[] = [];
  newCoupon: Coupon = new Coupon(null, "", null, null, null, "", "", null, "");
  newCoupons: Coupon[] = []; 
  //couponTypes = ['RESTORANS', 'ELECTRICITY', 'FOOD', 'HEALTH', 'SPORTS', 'CAMPING', 'TRAVELLING'];  

  constructor(private companyService: CompanyService) { }

  ngOnInit() {
    this.getAllCoupons();
  }

  getAllCoupons(): void {
    this.companyService.getAllCoupons()
    .subscribe(coupons => this.coupons = coupons);
  }

  createNewCoupon(coupon: Coupon): void {
    this.companyService.createCoupon(coupon)
    .subscribe((coupon) => {
      console.log(coupon)
      this.companyService.getAllCoupons()
    .subscribe(coupons => this.coupons = coupons)
     } ); 
}

updateCoupon(coupon: Coupon): void {
  this.companyService.updateCoupon(coupon)
  .subscribe((coupon) =>  {
   console.log(coupon)
   this.companyService.getAllCoupons()
   .subscribe(coupons => this.coupons = coupons) 
  });
}

removeCoupon(id: number ): void {
  this.companyService.removeCoupon(id).subscribe(data => {
    this.companyService.getAllCoupons()
   .subscribe(coupons => this.coupons = coupons)
  });   
}

getCoupon(id: number) {
  this.companyService.getCoupon(this.newCoupon.id)
  .subscribe(newCoupon => this.newCoupon = newCoupon);
}

getCouponsByPrice(topPrice: number) {
  this.companyService.getAllCouponsByPrice(this.newCoupon.price)
  .subscribe(newCoupons => this.newCoupons = newCoupons);
}

getCouponsByEndDate(endDate: Date) {
  this.companyService.getAllCouponsByDate(this.newCoupon.endDate)
  .subscribe(newCoupons => this.newCoupons = newCoupons);
}

Logout(): void {
  this.companyService.Logout()
  .subscribe((resp)=>{
    console.log(resp)
    window.location.href='http://localhost:8080';
  })
}

}
