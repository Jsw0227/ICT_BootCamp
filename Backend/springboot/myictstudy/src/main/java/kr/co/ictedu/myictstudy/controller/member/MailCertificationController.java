package kr.co.ictedu.myictstudy.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.myictstudy.dao.CertificationNumberRedisDao;
import kr.co.ictedu.myictstudy.service.EmailSenderService;
import kr.co.ictedu.myictstudy.vo.EmailCheckVO;
import kr.co.ictedu.myictstudy.vo.EmailCountCheckVO;

@RestController
@RequestMapping("/api/auth")
public class MailCertificationController {
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private CertificationNumberRedisDao certificationNumberRedisDao;
	@PostMapping("/emailCheck")
	public int sendEmail(@RequestBody EmailCheckVO email) {
		System.out.println("요청 처리됨" + email.getEmail());
		int checkEmail = emailSenderService.duplicateEmail(email.getEmail());
		if (checkEmail == 0) {
			// 중복된 이메일이 없을 때 메일을 전송한다.
			emailSenderService.sendEmail(email.getEmail());
			System.out.println("인증코드 : "+email.getCode() );
			return 0;
		} else {
			return 1;
		}
	}
	@PostMapping("/emailCheck/certification")
	public ResponseEntity<EmailCountCheckVO> verifyCertificationNumber(@RequestBody EmailCheckVO dto) {
	    boolean hasKey = certificationNumberRedisDao.hasKey(dto.getEmail());
	    int attempts = certificationNumberRedisDao.getAttempt(dto.getEmail());
	    if (!hasKey) { //stringRedisTemplate.opsForValue().set(email, authCode,Duration.ofSeconds(10000));
	        return ResponseEntity.ok(new EmailCountCheckVO(false, "expired")); //설정한 시간이 초과 되었다.
	    } else if (attempts >= 3) {
	        return ResponseEntity.ok(new EmailCountCheckVO(false, "exceeded")); //exceeded 인증코드 시도를 3번 초과 했다.
	    } else if (certificationNumberRedisDao.getCertifiRedisNumber(dto.getEmail()).equals(dto.getCode())) {
	        certificationNumberRedisDao.deleteCertifiRedisNumber(dto.getEmail());
	        return ResponseEntity.ok(new EmailCountCheckVO(true, "ok")); //정상이다.
	    } else {
	        certificationNumberRedisDao.increaseAttempt(dto.getEmail());
	        return ResponseEntity.ok(new EmailCountCheckVO(false, "wrong"));
	    }
	}
}
