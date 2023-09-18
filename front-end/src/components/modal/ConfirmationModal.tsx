interface ConfirmationModalProps {
    isOpen: boolean;
    onCancel: () => void;
    onConfirm: (e: any) => void;
  }
  
  const ConfirmationModal: React.FC<ConfirmationModalProps> = ({ isOpen, onCancel, onConfirm }) => {
    if (!isOpen) {
      return null;
    }
  
    return (
        <div className="fixed inset-0 flex items-center justify-center z-50">
          <div className="modal-overlay fixed inset-0 bg-gray-900 opacity-90 rounded-lg"></div>
          <div className="modal p-4 rounded shadow-lg z-10">
            <p className="text-lg white font-semibold mb-4">Are you sure?</p>
            <div className="flex justify-center space-x-4">
              <button
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700"
                onClick={onConfirm}
              >
                Yes
              </button>
              <button
                className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-700"
                onClick={onCancel}
              >
                No
              </button>
            </div>
          </div>
        </div>
      );
    };
    
  
  export default ConfirmationModal;