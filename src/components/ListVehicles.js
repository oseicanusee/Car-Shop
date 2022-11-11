import React, { useEffect, useState } from 'react'
import VehicleService from "../services/VehicleService"
import { DataGrid, Button } from '@mui/x-data-grid'
import { color } from '@mui/system'
import VehicleCard from './VehicleCard'

function ListVehicles(){
  const[vehicles, setVehicles] = useState([])

  useEffect(() => {
    VehicleService.getAllVehicles()
    .then((response) => {
      setVehicles(response.data)
      console.log(response.data)
    }).catch(error => console.error(error))
  } , [])


  return (
    <div className='container'>
       <div className = "row">
            {vehicles.map(vehicle => (
                <VehicleCard key={vehicle.id} vehicle={vehicle}/>
            ))}
     </div>
      </div>
  )
    }
export default ListVehicles