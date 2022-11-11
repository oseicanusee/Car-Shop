import React from "react";
import { useForm } from 'react-hook-form'
import { useState, useEffect } from "react";
import "./css/login.css"
import AuthService from "../services/VehicleService"
import axios from "axios"

export default function LoginForm() {
  const { register, handleSubmit, watch, formState: { errors } } = useForm()
  const API_URL = 'http://localhost:8080/api/auth/'
  const[loading, setLoading] = useState(false);
  const [message, setMessage] = useState("")

  function onSubmit(userDetails){
    console.log(userDetails)
    
    try{
        const response = axios.post(API_URL + "login", userDetails);
        localStorage.setItem("jwtToken", response.data.token)
        setLoading(true);
        return Promise.resolve(response);
    
    } catch(error){
      return Promise.reject(error);
    }
  }

  return (
    <section>
        <div className="login">
            <div className="col-1">
                <h2>Sign In</h2>
                <form id='login-form' className='flex flex-col' onSubmit={handleSubmit(onSubmit)}>
                    <input type="text" {...register("username")} placeholder='username' />
                    <input type="text" {...register("password")} placeholder='password' />
                    {errors.username?.type === "required" && "Username is required"}
                    {errors.password?.type === "maxLength" && "Max Length Exceed"}
                    <button className='btn'>Sign In</button>
                </form>

            </div>
        </div>
    </section>
  )
}