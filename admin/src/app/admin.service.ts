import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Company } from './common/company';
import { Customer } from './common/customer';

import { catchError, map, tap } from 'rxjs/operators';
 

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  //Company requests
    
  createCompany(company: Company): Observable<Company> {
    return this.http.post<Company>('http://localhost:8080/newcompany', company, httpOptions);
    }

    getCompany(id: number): Observable<Company> {
      return this.http.get<Company>('http://localhost:8080/getcompany/' + id, httpOptions);
    } 
 
  
    removeCompany(id: number): Observable<Company> {
      return this.http.delete<Company>('http://localhost:8080/deletecompany/' + id, httpOptions)
    }

    updateCompany(company: Company): Observable<Company> {
      return this.http.put<Company>('http://localhost:8080/updatecompany', company, httpOptions);
    }

    getAllCompanies(): Observable<Company[]> {
      return this.http.get<Company[]>('http://localhost:8080/getallcompanies', httpOptions);
    }


    //Customer requests

    createCustomer(customer: Customer): Observable<Customer> {
      return this.http.post<Customer>('http://localhost:8080/createcustomer', customer, httpOptions);
      }
  
      getCustomer(id: number): Observable<Customer> {
        return this.http.get<Customer>('http://localhost:8080/getcustomer/' + id, httpOptions);
      } 
   
    
      removeCustomer(id: number): Observable<Customer> {
        return this.http.delete<Customer>('http://localhost:8080/deletecustomer/' + id, httpOptions)
      }
  
      updateCustomer(customer: Customer): Observable<Customer> {
        return this.http.put<Customer>('http://localhost:8080//updatecustomer', customer, httpOptions);
      }
  
      getAllCustomers(): Observable<Customer[]> {
        return this.http.get<Customer[]>('http://localhost:8080/getallcustomers', httpOptions);
      }
  
  
       // logout session end return to login page
    Logout(){
      return  this.http.post('http://localhost:8080/customer/companylogout', null);
      }
 

}
