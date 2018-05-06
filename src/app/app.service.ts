
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.post('http://localhost:8252/authserver/login',
        {userName:credentials.username,password:credentials.password}).subscribe(response => {
            if (response) {
                localStorage.setItem("jwToken",response['entity']);
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }

     register(credentials, callback) {
        this.http.post('http://localhost:8252/authserver/register',
        {userName:credentials.username,password:credentials.password}).subscribe(response => {
            if (response) {
                console.log(response);
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }


}