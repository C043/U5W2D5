package fragnito.U5W2D5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.exceptions.BadRequestException;
import fragnito.U5W2D5.exceptions.NotFoundException;
import fragnito.U5W2D5.payloads.NewDipendenteDTO;
import fragnito.U5W2D5.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Dipendente saveDipendente(NewDipendenteDTO body) {
        if (this.dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("Esiste già un utente con questa email.");
        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        newDipendente.setAvatar("https://ui-avatars.com/api/?name=" + newDipendente.getNome() + "+" + newDipendente.getCognome());
        this.dipendenteRepository.save(newDipendente);
        return newDipendente;
    }

    public List<Dipendente> getAllDipendenti() {
        return this.dipendenteRepository.findAll();
    }

    public Dipendente getDipendenteById(int id) {
        return this.dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente updateDipendente(int id, NewDipendenteDTO updatedDipendente) {
        Dipendente found = this.getDipendenteById(id);
        found.setNome(updatedDipendente.nome());
        found.setCognome(updatedDipendente.cognome());
        found.setEmail(updatedDipendente.email());
        found.setUsername(updatedDipendente.username());
        this.dipendenteRepository.save(found);
        return found;
    }

    public void deleteDipendente(int id) {
        Dipendente found = this.getDipendenteById(id);
        this.dipendenteRepository.delete(found);
    }

    public void uploadImage(int id, MultipartFile img) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        Dipendente found = this.getDipendenteById(id);
        found.setAvatar(url);
        this.dipendenteRepository.save(found);
    }
}
