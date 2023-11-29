import React, { useState, useEffect } from 'react';

import Repository from './Repository.jsx';

export default function Body (){
    const [showDetails, setShowDetails] = useState(false);

    const [data, setData] = useState([]);

    useEffect(() => {
      async function fetchData() {
        try {
          const response = await fetch('http://localhost:8080/AvGit/repo');
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
  
          const responseData = await response.clone().json(); // Clone the response and parse JSON
          console.log('Response from http://localhost:8080/AvGit/repo', responseData);
          setData(responseData);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }  
      fetchData();
    }, []);

    const toggleDetails = () => {
        setShowDetails(!showDetails);
      };

    
    return(
        <div className="repo-container">
            <h3>My Repositories </h3>
                {   data.length === 0 ?( 
                <p>Data is empty</p>) : (
                
                    data.map((conceptItem,index) => (
                <Repository 
                    key={index} 
                    name={conceptItem.name} 
                />
                )))
            }

        </div>
    );
}