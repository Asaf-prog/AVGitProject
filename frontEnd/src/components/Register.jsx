import React, { useState } from 'react';

export default function Register() {
  const [registerInput, setRegisterInput] = useState({
    firstName: '',
    lastName: '',
    userName: '',
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    setRegisterInput({
      ...registerInput,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit =  (e) => {
    e.preventDefault();
    // Call the handleRegister function with the registerInput data
     handleRegister();
  };


  const handleRegister =  async () => {
    

    const url = "http://localhost:8080/AvGit/register";
      
    const userDTO = {
      firstName:registerInput.firstName,
      lastName:registerInput.lastName,
      userName: registerInput.userName, 
      email: registerInput.email,
      password: registerInput.password
  };

  try{
    console.log(userDTO.firstName);

    const response = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body:JSON.stringify(userDTO)
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const responseData = await response.json();
    console.log('Response from http://localhost:8080/AvGit/register', responseData);

    console.log(responseData.userName);
    console.log(responseData.password);

  }catch(error){
    console.log('An error occurred: ', error.message);
  }


};

 

  return (
    <div className="register-form">
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>First Name:</label>
          <input
            type="text"
            name="firstName"
            value={registerInput.firstName}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Last Name:</label>
          <input
            type="text"
            name="lastName"
            value={registerInput.lastName}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>User Name:</label>
          <input
            type="text"
            name="userName"
            value={registerInput.userName}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={registerInput.email}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={registerInput.password}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit">Register</button>
      </form>
    </div>
  );
}
