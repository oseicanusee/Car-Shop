import axios from "axios"

const API_URL = 'http://localhost:8080/api/auth/'

export default class AuthService {

  login(userDetails){
      return axios
      .post(API_URL + "signin",
          userDetails
      ).then(response => 
        {if(response.data.accessToken){
            localStorage.setItem("user", Json.stringify(response.data));
      }
        return response.data
    })
  }

  logout(){
    localStorage.removeItem("user");
  }

  const register = (user) => {
        return axios.post(API_URL + "register", user)
    }
  

  const getCurrentUser = () => {
          return JSON.parse(localStorage.getItem("user"));
    };
        
   const AuthService = {
          register,
          login,
          logout,
          getCurrentUser,
    };
}