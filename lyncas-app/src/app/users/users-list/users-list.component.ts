import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { UsersService } from 'src/app/users.service';
import { User } from '../user';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']


})
export class UsersListComponent implements OnInit {

  users: User[] = [];
  userSelecionado: User;
  mensagemSucesso: string;
  mensagemErro: string;
  name: string;
  totalElements = 0;
  pagina = 0;
  tamanho = 5;
  pageSizeOptions: number[] = [5];

  constructor(private service: UsersService, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {

    this.listar(this.pagina, this.tamanho);

  }

  listar(pagina = 0, tamanho = 5) {
    this.service.getUsers(pagina, tamanho)
      .subscribe(response => {
        this.users = response.content;
        this.totalElements = response.totalElements;
        this.pagina = response.number;
      })
  }

  paginar(event: PageEvent) {
    this.pagina = event.pageIndex;
    this.listar(this.pagina, this.tamanho)
  }

  novoCadastro() {
    this.router.navigate(['/cadastro'])
  }

  consultar() {
    this.service.buscar(this.name)
      .subscribe(response =>
        this.users = response)
  }

  preparaDelecao(user: User) {
    this.userSelecionado = user;
  }

  deletarUsuario() {
    this.service.deletar(this.userSelecionado)
      .subscribe(response => {
        this.mensagemSucesso = "Usuário deletado com sucesso!",
          setTimeout(() => {
            if (this.authService.getUsuarioAutenticado() == this.userSelecionado.login) {
              this.authService.encerrarSessao();
              this.router.navigate(['/login'])
            } else {
              this.ngOnInit();
              this.mensagemSucesso = null;
            }
          }, 1500)
      },

        erro => {
          this.mensagemErro = "Ocorreu um erro ao deletar o usuário.",
            setTimeout(() => {
              this.ngOnInit();
              this.mensagemErro = null;
            }, 1500)
        },

      )
  }
}


