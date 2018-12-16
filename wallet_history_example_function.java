    @GetMapping("me")
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    public List<WalletHistoryResponse> getWalletHistoriesForCurrentPatient(@CurrentUser CustomUserDetails currentUser) {
        Optional<User> user = userRepository.findById(currentUser.getId());
        Patient patient = patientRepository.findByUser(user.get()).get();
        Wallet wallet = walletRepository.findByPatientId(patient.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patient.getId()));
        List<WalletHistory> walletHistories = walletHistoryRepository.findAllByWalletId(wallet.getId());
        List<WalletHistoryResponse> walletHistoryResponses = new ArrayList<>();
        walletHistories.forEach(walletHistory -> walletHistoryResponses.add(getWalletHistoryResponse(walletHistory)));
        return walletHistoryResponses;
    }
