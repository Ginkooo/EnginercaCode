@Autowired
private UserRepository userRepository; // wstrzykujemy repytorium

@Autowired
private PatientRepository patientRepository; // wstrzykujemy repozytorium

...

@GetMapping("/patient/me")
@PreAuthorize("hasRole('ROLE_PATIENT')")
public CurrentPatientResponse getCurrentPatientInfo(@CurrentUser CustomUserDetails currentUser) {
    Optional<User> user = userRepository.findById(currentUser.getId()); // używamy instacji wczytanego repozytorium
    Patient patient = patientRepository.findByUser(user.get()).get(); // używamy instacji wczytanego repozytorium
	...
}
