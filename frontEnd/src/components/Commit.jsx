import  { useState, useEffect } from 'react';
import CommitData from './CommitData.jsx';
export default function Commit({repoName}){
    const [data, setData] = useState([]);
    useEffect(() => {
        async function fetchData() {
          try {
            
            const queryParams = new URLSearchParams();
           console.log('check query param =>',repoName);
           // queryParams.append('repoName', repoName);
            //queryParams.append('param2', 'value2');
      
            //const url = `http://localhost:8080/commit?${queryParams.toString()}`;
            const url = "http://localhost:8080/AvGit/commit";
      
            const response = await fetch(url);
      
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
      
            const responseData = await response.json();
            console.log('Response from http://localhost:8080/AvGit/commit', responseData);
            setData(responseData);
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        }
      
        fetchData();
      }, []);
 
      return (
        <>
        <p>here in commit component</p>
        <p>{repoName}</p>
        <ul id="nameList">
            {data.length === 0 ? null : data.map((conceptItem) => (
            <CommitData  key={conceptItem.sha1} commitContent={conceptItem.name} commitSh1 ={conceptItem.sha1} commitData ={conceptItem.date} commitAuthor = {conceptItem.author} />
            ))}
            
            </ul>
        </>
    );
}
//send http request to see the list of the commit
// we have the name of the repo and the name of the user and we take from the server the list of the commits