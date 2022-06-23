import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from 'src/app/users.service';
import { User } from '../user';


@Component({
  selector: 'app-users-form',
  templateUrl: './users-form.component.html',
  styleUrls: ['./users-form.component.css']
})
export class UsersFormComponent implements OnInit {

  user: User;
  success: boolean = false;
  errors: String[];
  id: number;
  repeatPassword: string;


  constructor(private service: UsersService, private activatedRoute: ActivatedRoute,
    private router: Router, private formBuilder: FormBuilder) {
    this.user = new User();
  }

  ngOnInit(): void {
    let params = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service.getUserById(this.id)
          .subscribe(response => this.user = response,
            errorResponse => this.user = new User());
      }
    })

  }

  onSubmit() {
    if (this.id) {
      this.service.atualizar(this.user)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
          setTimeout(() => {
            this.router.navigate(['/lista'])
          }, 2000)
        }, errorResponse => {
          this.errors = errorResponse.error.errors;
          setTimeout(() => {
            this.errors = null
          }, 3000)
        })


    } else {
      this.service.salvar(this.user)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
          setTimeout(() => {
            this.router.navigate(['/lista'])
          }, 2000)
        }, errorResponse => {
          this.success = false;
          this.errors = errorResponse.error.errors;
          setTimeout(() => {
            this.errors = null
          }, 3000)
        })
    }
  }

}

