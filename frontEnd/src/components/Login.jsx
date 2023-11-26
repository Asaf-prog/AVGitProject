import { useState } from 'react';
import { json } from 'react-router-dom';
import Register from './Register.jsx';

export default function Login({onChange,name}) {
  
  const [userInput, setUserInput] = useState({
    userName: '',
    password: '',
  });

  const [showRegister, setShowRegister] = useState(false);

  const handleRegisterClick =  async (registerInput) => {
    
    setShowRegister(true);
};

  const handleUsernameChange = (e) => {
    setUserInput({
      ...userInput,
      userName: e.target.value,
    });
  };

  const handlePasswordChange = (e) => {
    setUserInput({
      ...userInput,
      password: e.target.value,
    });
  };

  const  handleLogin = async () => {
        
    const url = "http://localhost:8080/AvGit/login";
      
    const userDTO = {
      userName: userInput.userName, 
      password: userInput.password
  };

  try{
    console.log(userDTO.userName);

    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body:JSON.stringify(userDTO)
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const responseData = await response.json();
    console.log('Response from http://localhost:8080/AvGit/login', responseData);

    console.log(responseData.userName);
    console.log(responseData.password);


    
    if (userInput.userName === responseData.userName && userInput.password === responseData.user.password) {
      onChange();
      name.user = userInput.userName;
      //send request
    } else {
      // Handle login failure
      alert('Login failed. Please check your credentials.');
    }
  }catch(error){
    console.log('An error occurred: ', error.message);
  }
  };

  return (
    <span id="user-input">
      <h2>Login</h2>
      <div className="input-group">
       
      {! showRegister && (
       <>
       
       <label>User Name</label>
        <input
          type="text"
          placeholder="Username"
          value={userInput.userName}
          onChange={handleUsernameChange}
          className="input-field"
        />

        <label>Password</label>
        <input
          type="password"
          placeholder="Password"
          value={userInput.password}
          onChange={handlePasswordChange}
          className="input-field"
        />

       </>
      )}
        
      {! showRegister && (
        <button onClick={handleLogin} className="login-button">
          Login
        </button>
      )}
        {showRegister && (
        <div className="register-container">
          <h2>Register</h2>
          {<Register />}
        </div>
        )}
        {!showRegister && (
        <button onClick={handleRegisterClick} className="login-button">
          Register
        </button>
      )}
      </div>
    </span>
  );
}
