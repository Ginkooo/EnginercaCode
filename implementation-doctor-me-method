    @GetMapping("/doctor/me")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public CurrentDoctorResponse getCurrentDoctorInfo(@CurrentUser CustomUserDetails currentUser) {
        User user = userRepository.findById(currentUser.getId()).get();
        Doctor doctor = doctorRepository.findByUser(user).get();

        CurrentDoctorResponse currentDoctorResponse = new CurrentDoctorResponse(currentUser.getId(),
                doctor.getId(),
                currentUser.getUsername(),
                currentUser.getMail(),
                currentUser.getFirstName(),
                currentUser.getLastName(),
                currentUser.getSecondName(),
                currentUser.getPhone(),
                doctor.getAcademicTitle(),
                doctor.isActive(),
                doctor.getSpecializations()
        );

        return currentDoctorResponse;
    }
