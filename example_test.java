    @Test
    public void createVisitTest() throws Exception {
        given(patientRepository.findById(1L)).willReturn(Optional.of(patient1));
        given(doctorRepository.findById(1L)).willReturn(Optional.of(doctor1));
        given(walletRepository.findByPatientId(1L)).willReturn(Optional.of(wallet));
        when(visitRepository.save(any(Visit.class))).thenReturn(visit1);
        when(walletRepository.save(any(Wallet.class))).thenReturn(new Wallet());
        when(walletHistoryRepository.save(any(WalletHistory.class))).thenReturn(new WalletHistory());
        mockMvc.perform(post("/api/visit").with(user("admin1")
                .roles("ADMIN")).content(exampleVisitJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDoctor", is(1)));
    }
