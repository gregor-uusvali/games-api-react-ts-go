import React from 'react';

interface OutletContextType {
    jwtToken: string;
    setJwtToken: React.Dispatch<React.SetStateAction<string>>;
    alertClassName: string;
    setAlertClassName: React.Dispatch<React.SetStateAction<string>>;
    alertMessage: string;
    setAlertMessage: React.Dispatch<React.SetStateAction<string>>;
    alertType: string;
    setAlertType: React.Dispatch<React.SetStateAction<string>>;
}

export const OutletContext = React.createContext<OutletContextType | undefined>(undefined);

export const useOutletContext = () => {
  const context = React.useContext(OutletContext);
  if (context === undefined) {
    throw new Error('useOutletContext must be used within an OutletContextProvider');
  }
  return context;
};