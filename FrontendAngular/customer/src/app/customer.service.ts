import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {Coupon} from './common/coupon';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  // services for customer page

  purchaseCoupon(coupon: Coupon): Observable<Coupon> {
    return this.http.post<Coupon>('http://localhost:8080/purchasecoupon', coupon, httpOptions);
    }

    getAllMyCoupons(): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallmycoupons', httpOptions);
    }

    getAllMyCouponsByType(type: String): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallmycouponbytype/' + type, httpOptions);
    }

    getAllMyCouponsByPrice(topPrice: number): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallmycouponbyprice/' + topPrice, httpOptions);
    }

    getAllMyCouponsByDate(endDate: Date): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallmycouponbyenddate/' + endDate, httpOptions);
    }

    getAllCouponsList(): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallcouponslist', httpOptions);
    }

    // logout session end return to login page
    Logout(){
        return  this.http.post('http://localhost:8080/customer/customerlogout', null);
        }
    
    
}
