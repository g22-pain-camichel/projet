package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Benevole;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Memo;
import projet.data.Participant;
import projet.data.Permi;
import projet.data.Personne;
import projet.data.Service;
import projet.data.Tache;


@Mapper
public interface IMapper {  
	
	Participant update( @MappingTarget Participant courant, Participant participant  );
	
	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Memo update( @MappingTarget Memo target, Memo source );

	Service update( @MappingTarget Service target, Service source );
	
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	EquipeBenevole update( @MappingTarget EquipeBenevole target, EquipeBenevole source );
	
	Permi update( @MappingTarget Permi target, Permi source );
	
	Epreuve update( @MappingTarget Epreuve target, Epreuve source );
	
	Tache update( @MappingTarget Tache target, Tache source );
}
