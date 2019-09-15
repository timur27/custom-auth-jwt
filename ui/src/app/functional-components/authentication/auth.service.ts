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

    public registerUser(username: string, password: string): Observable<any> {
        return this.http.post<any>(url + UrlConstantsComponent.REGISTER, {username, password}, this.getHeaders());
    }

    public loginUser(username: string, password: string): Observable<any> {
       return this.http.post<any>(url + UrlConstantsComponent.LOGIN, JSON.stringify({username, password}), this.getHeaders());
    }

    private getHeaders() {
        let httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
        let options ={
            headers: httpHeaders
        };
        return options;
    }
}
