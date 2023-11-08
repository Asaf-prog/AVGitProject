import Repo from './Repo.jsx';
import  { useState, useEffect } from 'react';
export default function Header(){
    const [data, setData] = useState([]);

    useEffect(() => {
      async function fetchData() {
        try {
          const response = await fetch('http://localhost:8080/data');
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
  
          const responseData = await response.clone().json(); // Clone the response and parse JSON
          console.log('Response from http://localhost:8080/data:', responseData);
          setData(responseData);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }  
      fetchData();
    }, []);
    return(
        
        <main>
            <section id="core-concepts" className='scrollable-container'>
            <h2>My-Repo</h2>
            <ul id="nameList">
            {data.length === 0 ? <p>Data is empty</p> : data.map((conceptItem) => (
            <Repo key={conceptItem.index} name={conceptItem.name} index={conceptItem.index} />
            ))}
            
            </ul>
            </section> 
        </main>
        
    );
}
