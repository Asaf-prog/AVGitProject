import React, { useState, useEffect } from 'react';

import Repository from './Repository.jsx';

export default function Body ({userName,userPassword}){

    const [data, setData] = useState([]);

    const userDTO = {
      userName: userName, 
      password: userPassword.password
    }
    useEffect(() => {
      async function fetchData() {
        try {

          const url = "http://localhost:8080/AvGit/reposUser";
          const response = await fetch(url,{
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body:JSON.stringify(userDTO)

          });
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
  
          const responseData = await response.clone().json(); // Clone the response and parse JSON
          console.log('Response from http://localhost:8080/AvGit/reposUser', responseData);
          setData(responseData);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }  
      fetchData();
    }, []);

    
    return(
        <div className="repo-container">
            <h3>My Repositories </h3>
                {   data.length === 0 ?( 
                <p>Data is empty</p>) : (
                
                    data.map((conceptItem,index) => (
                <Repository 
                    key={index} 
                    name={conceptItem.name}
                    userPassword={userPassword}
                    path = {conceptItem.path} 
                />
                )))
            }

        </div>
    );
}