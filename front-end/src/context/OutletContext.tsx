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
}

export const OutletContext = React.createContext<OutletContextType | undefined>(undefined);

export const useOutletContext = () => {
  const context = React.useContext(OutletContext);
  if (context === undefined) {
    throw new Error('useOutletContext must be used within an OutletContextProvider');
  }
  return context;
};