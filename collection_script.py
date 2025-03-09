# https://cwe-api.mitre.org/api/v1/cwe/weakness/79
import os
import requests


def fetch_cwe_examples(cwe_ids):
    base_url = "https://cwe-api.mitre.org/api/v1/cwe/weakness/"
    acceptable_languages = {
        "PHP": ".php",
        "JavaScript": ".js",
        "Java": ".java",
        "TypeScript": ".ts",
        "Python": ".py",
        "Perl": ".pl",
        "Ruby": ".rb",
    }
    comment_syntax = {
        "PHP": "//",
        "JavaScript": "//",
        "Java": "//",
        "TypeScript": "//",
        "Python": "#",
        "Perl": "#",
        "Ruby": "#",
    }
    count = 0

    for cwe_id, issue in cwe_ids:
        url = f"{base_url}{cwe_id}"
        try:
            response = requests.get(url)
            response.raise_for_status()
            data = response.json()

            examples = data.get("Weaknesses", [{}])[
                0].get("DemonstrativeExamples", [])

            if not examples:
                print(f"No examples found for CWE-{cwe_id}.")
                continue

            folder_name = f"CWE-{cwe_id}"
            os.makedirs(folder_name, exist_ok=True)

            for idx, example in enumerate(examples):
                language_for_example = ""
                extra_content = ""
                comment_symbol_for_example = ""
                for entry in example.get("Entries", []):
                    language = entry.get("Language")
                    code = entry.get("ExampleCode")
                    nature = entry.get("Nature")
                    intro_text = entry.get("IntroText")
                    body_text = entry.get("BodyText")

                    # Add text
                    if intro_text:
                        extra_content += intro_text
                    if body_text:
                        extra_content += (f"\n{comment_symbol_for_example} {body_text}")

                    # If attack nature code, add to context.
                    if nature == "Attack":
                        extra_content += (f"\n{comment_symbol_for_example} {code}")

                    # If Nature is Bad, and language is acceptable, set it as language for the example.
                    if nature == "Bad" and language in acceptable_languages:
                        language_for_example = language
                        comment_symbol_for_example = comment_syntax[language_for_example]
                    else:
                        continue

                    # If it is code, then append to the file.
                    if code:
                        file_extension = acceptable_languages[language]
                        file_path = os.path.join(
                            folder_name, f"example_{idx}{file_extension}")

                        with open(file_path, "a", encoding="utf-8") as file:
                            file.write(code)

                        print(f"Saved example for CWE-{cwe_id} in {file_path}")

                if not language_for_example:
                    continue
                count += 1
                file_extension = acceptable_languages[language_for_example]
                file_path = os.path.join(
                    folder_name, f"example_{idx}{file_extension}")
                with open(file_path, "a", encoding="utf-8") as file:
                    # Result line
                    file.write(
                        f"\n\n\n{comment_symbol_for_example}Result: \n {comment_symbol_for_example} Vulnerable (01)")
                    # Gap
                    file.write(
                        f"\n{comment_symbol_for_example}\n{comment_symbol_for_example}\n{comment_symbol_for_example}\n{comment_symbol_for_example}")
                    # Issue
                    file.write(
                        f"{comment_symbol_for_example}Issue: CWE-{cwe_id} - {issue}")
                    # Gap
                    file.write(
                        f"\n{comment_symbol_for_example}\n{comment_symbol_for_example}\n{comment_symbol_for_example}\n{comment_symbol_for_example}")
                    # URL
                    file.write(f"URL: {url}")
                    # Gap
                    file.write(
                        f"\n{comment_symbol_for_example}\n\n{comment_symbol_for_example}")
                    # Source
                    file.write("Source: CWE Mitre")
                    # Gap
                    file.write(
                        f"\n{comment_symbol_for_example}\n\n{comment_symbol_for_example}")
                    # Extra context
                    file.write(
                        f"Extra context: ")
                    file.write(
                        f"\n{comment_symbol_for_example} {extra_content}")

        except requests.exceptions.RequestException as e:
            print(f"Error fetching data for CWE-{cwe_id}: {e}")


web_cwe_ids = [
    (79,   "Cross-Site Scripting (XSS)"),
    (89,   "SQL Injection"),
    (434,  "Unrestricted File Upload"),
    (352,  "Cross-Site Request Forgery (CSRF)"),
    (601,  "Open Redirect"),
    (22,   "Path Traversal"),
    (94,   "Code Injection"),
    (502,  "Deserialization of Untrusted Data"),
    (287,  "Improper Authentication"),
    (306,  "Missing Authentication for Critical Function"),
    (798,  "Use of Hard-coded Credentials"),
    (862,  "Missing Authorization"),
    (863,  "Incorrect Authorization"),
    (918,  "Server-Side Request Forgery (SSRF)"),
    (400,  "Uncontrolled Resource Consumption (DoS)"),
    (614,  "Sensitive Data in URL"),
    (311,  "Missing Encryption of Sensitive Data"),
    (759,  "Use of a One-Way Hash without a Salt"),
    (807,  "Reliance on Untrusted Inputs for Security Decisions"),
    (532,  "Exposure of Sensitive Information in Logs"),
    (200,  "Exposure of Sensitive Information"),
    (201,  "Information Exposure Through Sent Data"),
    (319,  "Cleartext Transmission of Sensitive Information"),
    (120,  "Buffer Overflow"),
    (704,  "Incorrect Type Validation"),
    (915,  "Improperly Controlled Modification of Dynamically-Determined Object Attributes"),
    (756,  "Missing Custom Error Page"),
    (640,  "Weak Password Recovery Mechanism"),
    (829,  "Inclusion of Functionality from Untrusted Control Sphere")
]


fetch_cwe_examples(web_cwe_ids)
