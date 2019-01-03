import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin.service';
import { Company } from '../../common/company';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})

export class CompanyComponent implements OnInit {
  
  company: Company = new Company(null, "", "","");
  companies: Company[]=[];
  newCompany: Company = new Company(null, "", "", "");
  CompanyForShow: Company = new Company(null, "", "", "");
  resp: any;


  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getAllCompanies();
  }

  getAllCompanies(): void {
    this.adminService.getAllCompanies()
    .subscribe(companies => this.companies = companies);
  }

  createNewCompany(company: Company): void {
    this.adminService.createCompany(company)
    .subscribe((company) => {
      console.log(company)
      this.adminService.getAllCompanies()
    .subscribe(companies => this.companies = companies)
     } ); 
}

updateCompany(company: Company): void {
  this.adminService.updateCompany(company)
  .subscribe((company) =>  {
   console.log(company)
    this.adminService.getAllCompanies()
    .subscribe(companies => this.companies = companies) 
  });
}

removeCompany(id: number ): void {
  this.adminService.removeCompany(id).subscribe(data => {
    this.adminService.getAllCompanies().subscribe(companies =>
       this.companies = companies)
  });   
}

getCompany(id: number) {
  this.adminService.getCompany(this.newCompany.id)
  .subscribe(CompanyForShow => this.CompanyForShow = CompanyForShow);
}

}