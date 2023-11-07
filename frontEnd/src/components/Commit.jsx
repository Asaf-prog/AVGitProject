import  { useState, useEffect } from 'react';
export default function Commit({repoName}){
    const [data, setData] = useState([]);
    useEffect(() => {
        async function fetchData() {
          try {
            
            const queryParams = new URLSearchParams();
            queryParams.append('repoName', repoName);
            //queryParams.append('param2', 'value2');
      
            const url = `http://localhost:8080/commit?${queryParams.toString()}`;
      
            const response = await fetch(url);
      
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
      
            const responseData = await response.json();
            console.log('Response from http://localhost:8080/commit:', responseData);
            setData(responseData);
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        }
      
        fetchData();
      }, []);
    return (
        <>
        <p>here</p>
        <p>{repoName}</p>
        <ul id="nameList">
            {data.length === 0 ? <p>Data is empty</p> : data.map((conceptItem) => (
            <Commit key={conceptItem.index} name={conceptItem.name} index={conceptItem.index} />
            ))}
            
            </ul>
        </>
    );
}
//stop in line 36 
//send http request to see the list of the commit
// we have the name of the repo and the name of the user and we take from the server the list of the commits