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
export class CompanyService {

  

  constructor(private http: HttpClient) { }

  createCoupon(coupon: Coupon): Observable<Coupon> {
    return this.http.post<Coupon>('http://localhost:8080/newcoupon', coupon, httpOptions);
    }

    getCoupon(id: number): Observable<Coupon> {
      return this.http.get<Coupon>('http://localhost:8080/getcoupon/' + id, httpOptions);
    } 
 
  
    removeCoupon(id: number): Observable<Coupon> {
      return this.http.delete<Coupon>('http://localhost:8080/deletecoupon/' + id, httpOptions)
    }

    updateCoupon(coupon: Coupon): Observable<Coupon> {
      return this.http.put<Coupon>('http://localhost:8080/updatecoupon', coupon, httpOptions);
    }

    getAllCoupons(): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallcoupons', httpOptions);
    }

    getAllCouponsByPrice(topPrice: number): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallcouponbyprice/' + topPrice, httpOptions);
    }

    getAllCouponsByDate(endDate: Date): Observable<Coupon[]> {
      return this.http.get<Coupon[]>('http://localhost:8080/getallcouponbyenddate/' + endDate, httpOptions);
    }

    // logout session end return to login page
    Logout(){
      return  this.http.post('http://localhost:8080/customer/companylogout', null);
      }

}
