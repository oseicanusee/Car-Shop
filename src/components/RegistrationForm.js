import React from 'react';
import {useForm} from 'react-hook-form';
import { useState,  } from 'react';
import Form from "react-validation/build/form";
import AuthService from '../services/VehicleService';
import axios from "axios"

 export default function RegistrationForm() {
  const {register, handleSubmit, watch, formState : { errors}} = useForm();
  const API_URL = 'http://localhost:8080/api/auth/'
  const[loading, setLoading] = useState(false);
  const [message, setMessage] = useState("")


  function onSubmit(userDetails)  {
      const response = axios.post(API_URL + "register", userDetails)
      console.log(response);
      // try to set errors. with the data returning console.log(response);
    }
  

  return(
    <div className='User-form-container'>
      <form className='User-form' onSubmit={handleSubmit(onSubmit)}>
      <div className='User-form-content'>
       <h3 className='User-form-title'>Register</h3>
       <div className="text-center">
            Already registered?{" "}
              Sign In
      </div> 
      <div className='form-group mt-3'>
        <input
        {... register("first_name", 
            {required : 'First name is required', 
                minLength : {
                  value: 2,
                  message : "Min length is 2"
                }})}
                placeholder='First Name'
                name='first_name'
                type="text"
            />
      </div>
      <div className='form-group mt-3'>
          <input
            {... register("last_name", {required : 'Last name is required', 
                    minLength : {
                      value:4,
                      message:"Min length is 2"
                    }})}
            placeholder='Last Name'
            name='last_name'
            type="text"
          />
      </div>
        <div className='form-group mt-3'>
          <input
            {... register("email", {required : 
            "Email is required", 
            pattern: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i
            })}
            className='form-group mt-1'
            placeholder='Email'
            type="email"
            name='email'
          />
      </div>
      <div className='form-group mt-3'>
          <input
            {... register("username", {required : 'Username is required', 
            minLength : {
              value:4,
              message:"Min length is 2"
            }})}
            className='form-group mt-1'
            placeholder='Username'
            name='username'
            type="text"
          />
      </div>

      <div className='form-group mt-3'>
          <input
            {... register("password", 
            {required : 'Password is required', 
                    minLength : {
                      value:4,
                      message:"Min length is 6"
                    }}
            )}
            className='form-group mt-1'
            placeholder='Password'
            type="password"
            name='password'
          />
      </div>
      <div className='d-grip dap-2 mt-3'>
          <button type='submit' className='btn btn-primary'>
              Sign Up
          </button>
      </div>
      <p className='text-center mt-2'>
          Forgot <a href='#'>password?</a>
      </p>
      </div>
    </form>
  </div>
  )
  }
