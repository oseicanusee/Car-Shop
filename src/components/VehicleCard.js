import VehicleService from "../services/VehicleService"

const VehicleCard = ({vehicle}) => {

    let newClassName = 'color_bg'
    let bg_img = 'url(${vehicle.images})'

  return(
      <div className='card'>
           <div key={vehicle.id} className='wrapper'>
              <div className={newClassName}></div>
                    <img src={vehicle.imageURL} />
                
                <div className="cardInfo">
                    <h1>{vehicle.title}</h1>
                    <p className="details">{vehicle.transmission}</p>
                    <p className="details">{vehicle.drive_wheels}</p>
                    <div className="action">
                         <div className="priceGroup"> 
                            <p className="price new_price">${vehicle.price}</p>
                        </div>
                        <div className="cart">
                            <svg className="outCart" xmlns="<http://www.w3.org/2000/svg>" viewBox="0 0 64 64">
                                <path d="M2 6h10l10 40h32l8-24H16"></path>
                                <circle cx="23" cy="54" r="4"></circle>
                                <circle cx="49" cy="54" r="4"></circle>
                            </svg>
                        </div>
                    </div>
               </div>  
           </div>
      </div>

  )
}

export default VehicleCard