import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {UrlConstantsComponent} from "../../foundation/url.constants.component";
import {User} from "./user";


const url = environment.apiUrl;

@Injectable()
export class AuthService {
    constructor(private http: HttpClient) {
    }

    public registerUser(user: User): Observable<any> {
        let httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
        let options ={
          headers: httpHeaders
        };
        return this.http.post<User>(url + UrlConstantsComponent.REGISTER, user, options);
    }
}
