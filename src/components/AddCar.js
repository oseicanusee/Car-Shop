import React, { useEffect } from 'react';
import {useForm} from 'react-hook-form';
import { useState } from 'react';
import Form from "react-validation/build/form";
import VehicleService from '../services/VehicleService';
import {useHistory} from 'react-router-dom';


 export default function AddCar() {
  const {reset, register, handleSubmit, watch, formState : { errors, isSubmitSuccessful}} = useForm();

  function onSubmit(vehicle)  {
    VehicleService.saveCar(vehicle).then((response)=> {
      
    }).catch(error => console.log(error))
  }

  useEffect(() => {
    if(isSubmitSuccessful){
      reset({
          
      });
    }
  }, [isSubmitSuccessful, reset])
  
  return(
    
    <div className='User-form-container'>
      <form className='User-form' onSubmit={handleSubmit(onSubmit)}>
          <div className='User-form-content'>
          <h3 className='User-form-title'>Add Car</h3>

          <div className='form-group mt-3'>
            <input
            {... register("year", 
                {required : 'Year is required', 
                    minLength : {
                      value: 2,
                      message : "Min length is 2"
                    }})}
                    placeholder='Vehicle Year'
                    name='year'
                    type="number"
                />
          </div>

          <div className='form-group mt-3'>
              <input
                {... register("make", {required : 'Make is required', 
                        minLength : {
                          value:2,
                          message:"Min length is 2"
                        }})}
                placeholder='Vehicle Make'
                name='make'
                type="text"
              />
          </div>

            <div className='form-group mt-3'>
              <input
                {... register("model", {required : 'Model is required', 
                minLength : {
                  value:2,
                  message:"Min length is 2"
                }})}
                placeholder='Vehicle Model'
                type="text"
                name='model'
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("trim", {required : 'Trim is required', 
                minLength : {
                  value:2,
                  message:"Min length is 2"
                }})}
                placeholder='Vehicle Trim'
                name='trim'
                type="text"
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("price", {required : 'Price is required', 
                minLength : {
                  value:4,
                  message:"Min length is 2"
                }})}
                placeholder='Vehicle Price'
                name='price'
                type="text"
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("imageURL", {required : 'Image URL is required', 
                minLength : {
                  value:4,
                  message:"Min length is 2"
                }})}
                placeholder='Image URL'
                name='imageURL'
                type="text"
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("transmission", {required : 'Transmission is required', 
                minLength : {
                  value:4,
                  message:"Min length is 2"
                }})}
                placeholder='Transmission Type'
                name='transmission'
                type="text"
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("cylinder", {required : 'Cylinder is required', 
                minLength : {
                  value:1,
                  message:"Min length is 1"
                }})}
                placeholder='Cylinder'
                name='cylinder'
                type="text"
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("engine_size", {required : 'Engine Size is required', 
                minLength : {
                  value: 1,
                  message:"Min length is 1"
                }})}
                placeholder='Engine Size'
                name='engine_size'
                type="text"
              />
          </div>
          
          <div className='form-group mt-3'>
              <input
                {... register("fuel_Type", 
                {required : 'Fuel Type is required', 
                        minLength : {
                          value: 1,
                          message:"Min length is 1"
                        }}
                )}
                placeholder='Fuel Type'
                type="text"
                name='fuel_Type'
              />
          </div>
          <div className='form-group mt-3'>
              <input
                {... register("drive_wheels", 
                {required : 'Drive wheels is required', 
                        minLength : {
                          value: 1,
                          message:"Min length is 1"
                        }}
                )}
                placeholder='Drive Wheels'
                type="text"
                name='drive_wheels'
              />
          </div>
          <div className='d-grip dap-2 mt-3'>
              <button type='submit' className='btn btn-primary'>
                  Add Car
              </button>
          </div>
          </div>
    </form>
  </div>
  )
  }
