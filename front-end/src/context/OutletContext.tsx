import React from 'react';

interface OutletContextType {
    isAuthenticated: boolean;
    setIsAuthenticated: React.Dispatch<React.SetStateAction<boolean>>;
    jwtToken: string;
    setJwtToken: React.Dispatch<React.SetStateAction<string>>;
    sessionToken: string;
    setSessionToken: React.Dispatch<React.SetStateAction<string>>;
    alertClassName: string;
    setAlertClassName: React.Dispatch<React.SetStateAction<string>>;
    alertMessage: string;
    setAlertMessage: React.Dispatch<React.SetStateAction<string>>;
    alertType: string;
    setAlertType: React.Dispatch<React.SetStateAction<string>>;
    currentUserId: number; // Add currentUserId to the context
    setCurrentUserId: React.Dispatch<React.SetStateAction<number>>; // Add setCurrentUserId to the context
    firstName: string;
    setFirstName: React.Dispatch<React.SetStateAction<string>>;
    lastName: string;
    setLastName: React.Dispatch<React.SetStateAction<string>>;
    lastWatered: Date;
    setLastWatered: React.Dispatch<React.SetStateAction<Date>>;
    daysToWater: number;
    setDaysToWater: React.Dispatch<React.SetStateAction<number>>;
    nextDateToWater: Date;
    setNextDateToWater: React.Dispatch<React.SetStateAction<Date>>;
    daysLeftToWater: number;
    setDaysLeftToWater: React.Dispatch<React.SetStateAction<number>>;
}
export const OutletContext = React.createContext<OutletContextType | undefined>(undefined);

export const useOutletContext = () => {
  const context = React.useContext(OutletContext);
  if (context === undefined) {
    throw new Error('useOutletContext must be used within an OutletContextProvider');
  }
  return context;
};