import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin.service';
import { Customer } from '../../common/customer';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})

export class CustomerComponent implements OnInit {

  customer: Customer = new Customer(null, "","");
  customers: Customer[]=[];
  newCustomer: Customer = new Customer(null,"","");
  CustomerForShow: Customer = new Customer(null,"","");
  resp: any;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getAllCustomers();
  }

  getAllCustomers(): void {
    this.adminService.getAllCustomers()
    .subscribe(customers => this.customers = customers);
  }

  createNewCustomer(customer: Customer): void {
    this.adminService.createCustomer(customer)
    .subscribe((customer) => {
      console.log(customer)
      this.adminService.getAllCustomers()
    .subscribe(customers => this.customers = customers)
     } ); 
}

updateCustomer(customer: Customer): void {
  this.adminService.updateCustomer(customer)
  .subscribe((customer) =>  {
   console.log(customer)
   this.adminService.getAllCustomers()
   .subscribe(customers => this.customers = customers) 
  });
}

removeCustomer(id: number ): void {
  this.adminService.removeCustomer(id).subscribe(data => {
    this.adminService.getAllCustomers()
   .subscribe(customers => this.customers = customers)
  });   
}

getCustomer(id: number) {
  this.adminService.getCustomer(this.newCustomer.id)
  .subscribe(CustomerForShow => this.CustomerForShow = CustomerForShow);
}


}
